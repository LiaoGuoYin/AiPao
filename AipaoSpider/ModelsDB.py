from sqlalchemy import Column, Float, String
from sqlalchemy.dialects.mysql import INTEGER, LONGTEXT, VARCHAR
from sqlalchemy.ext.declarative import declarative_base

Base = declarative_base()
metadata = Base.metadata


class AipaoerError(Base):
    __tablename__ = 'aipaoer_error'

    IIDD = Column(String(40, 'utf8mb4_general_ci'), primary_key=True)
    _xuehao = Column(String(40, 'utf8mb4_general_ci'))
    _mnums = Column(INTEGER(10))
    CosttimeBefore = Column(VARCHAR(40))
    ReasonFmt = Column(VARCHAR(40))
    NoCountReason = Column(VARCHAR(40))
    Sex = Column(VARCHAR(40))
    Sunningfen = Column(VARCHAR(40))
    Sunningyouxiao = Column(VARCHAR(40))
    Xuehao = Column(VARCHAR(40))
    MNums = Column(INTEGER(10))
    UserName = Column(VARCHAR(40))
    SunnyId = Column(INTEGER(10))
    SchoolId = Column(VARCHAR(40))
    SportType = Column(VARCHAR(40))
    LinkPoint = Column(VARCHAR(40))
    Speed = Column(Float)
    CostTime = Column(Float)
    CostDistance = Column(Float)
    AvaLengths = Column(Float)
    CostCalorie = Column(Float)
    BuildDate = Column(VARCHAR(40))
    ResultDateFmt = Column(VARCHAR(40))
    ResultDate = Column(VARCHAR(40))
    EndTime = Column(VARCHAR(40))
    NickName = Column(VARCHAR(40))
    SchoolName = Column(VARCHAR(40))
    MapState = Column(INTEGER(10))
    GradeName = Column(VARCHAR(40))
    UserClass = Column(VARCHAR(40))
    Num = Column(INTEGER(10))
    WhiteNum = Column(INTEGER(10))
    BlackNum = Column(INTEGER(10))
    SumNum = Column(INTEGER(10))
    Scores = Column(INTEGER(10))
    ResultHour = Column(INTEGER(10))
    StepNum = Column(INTEGER(10))
    ClassName = Column(VARCHAR(40))
    CoutrseCode = Column(VARCHAR(40))
    CoutrseTeacher = Column(VARCHAR(40))
    ExtendedAttributesCount = Column(INTEGER(10))


class AipaoerInfo(Base):
    __tablename__ = 'aipaoer_info'

    userId = Column(INTEGER(11), primary_key=True)
    nickName = Column(String(20, 'utf8mb4_general_ci'))
    sex = Column(String(5, 'utf8mb4_general_ci'))
    schoolId = Column(String(10, 'utf8mb4_general_ci'))
    iidds = Column(LONGTEXT)


class AipaoerOk(Base):
    __tablename__ = 'aipaoer_ok'

    IIDD = Column(String(40, 'utf8mb4_general_ci'), primary_key=True)
    _xuehao = Column(String(40, 'utf8mb4_general_ci'))
    _mnums = Column(INTEGER(10))
    CosttimeBefore = Column(VARCHAR(40))
    Sex = Column(VARCHAR(40))
    Sunningfen = Column(VARCHAR(40))
    Sunningyouxiao = Column(VARCHAR(40))
    Xuehao = Column(VARCHAR(40))
    MNums = Column(INTEGER(10))
    UserName = Column(VARCHAR(40))
    SunnyId = Column(INTEGER(10))
    SchoolId = Column(VARCHAR(40))
    SportType = Column(VARCHAR(40))
    LinkPoint = Column(VARCHAR(40))
    Speed = Column(Float)
    CostTime = Column(Float)
    CostDistance = Column(Float)
    AvaLengths = Column(Float)
    CostCalorie = Column(Float)
    BuildDate = Column(VARCHAR(40))
    ResultDateFmt = Column(VARCHAR(40))
    ResultDate = Column(VARCHAR(40))
    EndTime = Column(VARCHAR(40))
    NickName = Column(VARCHAR(40))
    SchoolName = Column(VARCHAR(40))
    MapState = Column(INTEGER(10))
    GradeName = Column(VARCHAR(40))
    UserClass = Column(VARCHAR(40))
    Num = Column(INTEGER(10))
    WhiteNum = Column(INTEGER(10))
    BlackNum = Column(INTEGER(10))
    SumNum = Column(INTEGER(10))
    Scores = Column(INTEGER(10))
    ResultHour = Column(INTEGER(10))
    StepNum = Column(INTEGER(10))
    ClassName = Column(VARCHAR(40))
    CoutrseCode = Column(VARCHAR(40))
    CoutrseTeacher = Column(VARCHAR(40))
    ExtendedAttributesCount = Column(INTEGER(10))