from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker

from AipaoRunning.CheckValid import check_valid

Base = declarative_base()


def insert_db():
    engine = create_engine("mysql+pymysql://root:liaoguoyin@localhost:3306/aipao", echo=False)
    DBSession = sessionmaker(bind=engine)
    session = DBSession()

    IMEICodes = []
    with open("IMEICode.txt", "r") as fp:
        all_IMEICodes = fp.readlines()
        for IMEICode in all_IMEICodes:
            IMEICodes.append(IMEICode[:32])
    fp.close()

    for IMEICode in IMEICodes:
        student = check_valid(IMEICode)
        if student.isValid:
            session.merge(student)
            print(student)
        # else:
        #     print("IMEICode has inv   alid: {}".format(student.IMEICode))
    session.commit()


insert_db()
