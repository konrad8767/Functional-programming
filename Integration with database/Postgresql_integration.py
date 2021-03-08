# III. Wyznaczyć średni miesięczny przedział dziennych zachorowań na milion mieszkańców oraz pogrupować państwa na trzy kategorie (low, medium, high)  wg w.w. wymienionego przedziału, następnie wyświetlić wszystkie państwa, które polepszyły swoją sytuację od początku kwietnia do końca października.
# Azja

from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
from sqlalchemy import create_engine, Integer, DateTime
import pandas as pd
import psycopg2

try:
    db_string = "postgresql://postgres:postgres@localhost:5433/postgres"
    db = create_engine(db_string)
    base = declarative_base()

    dfContinent = pd.read_sql_table('continent', db)
    dfLocation = pd.read_sql_table('location', db)
    dfDate = pd.read_sql_table('date', db)
    dfNewCases = pd.read_sql_table('new_cases_per_million', db)

    #data = pd.concat([dfContinent, dfLocation, dfDate, dfNewCases], axis=1)
    data = dfContinent.merge(dfLocation, on='index')
    data = data.merge(dfDate, on='index')
    data = data.merge(dfNewCases, on='index')

    data = data.dropna()

    #print(data)

    data = data[data.continent.str.contains('Asia',case=False)]
    data['date'] = pd.to_datetime(data['date'])
    data['period'] = data['date'].dt.month
    data = data.loc[(data['period']>=4) & (data['period']<=10)]
    data = data.groupby(['location', 'period']).mean()
    data = data.reset_index()[['location', 'period', 'new_cases_per_million']]

    low = data.groupby('period', as_index=0)['new_cases_per_million'].min()
    high = data.groupby('period', as_index=0)['new_cases_per_million'].max()
    med = data.groupby('period', as_index=0)['new_cases_per_million'].max()

    groups = pd.DataFrame(data, columns = ['period', 'low', 'mediumLow','mediumHigh', 'high'])
    groups['period'] = data['period']
    groups['low'] = low['new_cases_per_million']
    groups['high'] = high['new_cases_per_million']
    groups['mediumLow'] = (high['new_cases_per_million'] - low['new_cases_per_million'])*(1/3)
    groups['mediumHigh'] = (high['new_cases_per_million'] - low['new_cases_per_million'])*(2/3)
    groups = groups.dropna()

    result = pd.merge(data, groups)

    result.loc[(result.new_cases_per_million < result.mediumLow), 'group'] = 1
    result.loc[(result.new_cases_per_million > result.mediumLow) & (result.new_cases_per_million < result.mediumHigh), 'group'] = 2
    result.loc[(result.new_cases_per_million > result.mediumHigh), 'group'] = 3

    result = result.drop(['new_cases_per_million', 'low', 'mediumLow', 'mediumHigh', 'high'], axis=1)
    result = result.loc[(result['period']==4) | (result['period']==10)]
    result = result.reset_index()[['location', 'period', 'group']]

    result = result.sort_values(by=['location', 'period'])
    result = result.reset_index()[['location', 'period', 'group']]
    result = result.groupby('location').last()['group'] - result.groupby('location').first()['group']
    result = result.reset_index()[['location', 'group']]

    result.loc[(result.group < 0), 'promotion'] = True
    result = result.drop(['group'], axis=1)
    result = result.dropna()
    result = result.reset_index()[['location']]


finally:
    print(result)