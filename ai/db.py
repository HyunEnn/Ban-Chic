import os
from contextlib import contextmanager

from sqlalchemy import create_engine
from sqlalchemy.orm.session import sessionmaker
from dotenv import load_dotenv

load_dotenv(verbose=True)
user = os.getenv("user")
password = os.getenv("pass")
host = os.getenv("host")
port = os.getenv("port")
database = os.getenv("database")

db_url = f"mysql+pymysql://{user}:{password}@{host}:{port}/{database}?charset=utf8"
Session = sessionmaker()

db = create_engine(db_url, pool_recycle=500)
Session.configure(bind=db, expire_on_commit=False)


@contextmanager
def session_scope():
    session = Session()
    try:
        yield session
        session.commit()
    except:
        print(db_url)
        print("!!!!!!!!!!!!!!!!db연결 장애!!!!!!!!!!!!!!")
        session.rollback()
        raise
    finally:
        session.close()