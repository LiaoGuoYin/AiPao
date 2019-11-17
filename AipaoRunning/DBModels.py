from sqlalchemy import Column, Float, String, TIMESTAMP, Table, Time, text
from sqlalchemy.dialects.mysql import BIGINT, INTEGER, TINYINT, VARCHAR
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
    BuildDate = Column(String(40, 'utf8mb4_general_ci'))
    ResultDateFmt = Column(VARCHAR(40))
    ResultDate = Column(String(40, 'utf8mb4_general_ci'))
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
    standardtime = Column(String(50, 'utf8mb4_general_ci'))


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
    BuildDate = Column(String(40, 'utf8mb4_general_ci'))
    ResultDateFmt = Column(VARCHAR(40))
    ResultDate = Column(String(40, 'utf8mb4_general_ci'))
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
    standardtime = Column(String(50, 'utf8mb4_general_ci'))


class Order(Base):
    __tablename__ = 'order'

    IMEICode = Column(String(64, 'utf8mb4_general_ci'), primary_key=True)
    isValid = Column(TINYINT(1))
    name = Column(String(64, 'utf8mb4_general_ci'))
    userId = Column(INTEGER(16))
    orderCounts = Column(String(64, 'utf8mb4_general_ci'))
    isMoney = Column(String(16, 'utf8mb4_general_ci'), server_default=text("'没给'"))
    allCounts = Column(INTEGER(16))
    morningCounts = Column(INTEGER(16))
    finishedCounts = Column(INTEGER(16))
    QQ = Column(INTEGER(16))
    isTodayOK = Column(TINYINT(1))
    lastTime = Column(Time)
    addTime = Column(TIMESTAMP, server_default=text("CURRENT_TIMESTAMP"))


t_school_notice = Table(
    'school_notice', metadata,
    Column('schoolld', BIGINT(20)),
    Column('schoolName', String(64, 'utf8mb4_general_ci'), nullable=False),
    Column('noticeURL', String(256, 'utf8mb4_general_ci'))
)


class StudentInfo(Base):
    __tablename__ = 'student_info'

    userId = Column(INTEGER(16), primary_key=True, unique=True)
    name = Column(String(128, 'utf8mb4_general_ci'))
    IMEICode = Column(String(64, 'utf8mb4_general_ci'))
    isValid = Column(TINYINT(1))
    sex = Column(String(16, 'utf8mb4_general_ci'))
    studentNumber = Column(String(64, 'utf8mb4_general_ci'))
    schoolMajor = Column(String(128, 'utf8mb4_general_ci'))
    allCounts = Column(INTEGER(16))
    morningCounts = Column(INTEGER(16))

    def __str__(self):
        return str(self.__dict__).replace("\'", "\"")
