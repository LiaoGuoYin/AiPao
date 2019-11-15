from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker

from AipaoSpider.ModelsDB import AipaoerOk, AipaoerError

Base = declarative_base()


def insert_db(aipaoer_dict):
    engine = create_engine("mysql+pymysql://root:liaoguoyin@localhost:3306/aipao", echo=False)
    DBSession = sessionmaker(bind=engine)
    session = DBSession()

    # data = AipaoerInfo(
    #     userId=aipaoer_dict["userId"],
    #     nickName=aipaoer_dict["nickName"],
    #     sex=aipaoer_dict["sex"],
    #     schoolId=aipaoer_dict["schoolId"],
    #     iidds="none"
    # )
    # session.add(data)
    # session.commit()

    ok_records = aipaoer_dict["okRecords"]
    for ok_record in ok_records:
        data = AipaoerOk(**ok_record)
        session.add(data)
        session.commit()

    error_records = aipaoer_dict["errorRecords"]
    for error_record in error_records:
        data = AipaoerError(**error_record)
        session.add(data)
        session.commit()
