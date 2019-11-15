class Aipaoer(object):

    def __init__(self, userId):
        self.userId = userId
        self.nickName = ""
        self.sex = "Unknown"
        self.schoolId = "Unknown"
        self.okRecords = []
        self.errorRecords = []

    def clear(self):
        self.userId = ""
        self.nickName = ""
        self.sex = "Unknown"
        self.schoolId = "Unknown"
        self.okRecords = []
        self.errorRecords = []

    def __str__(self):
        return str(self.__dict__).replace("\'", "\"") + "\n"
