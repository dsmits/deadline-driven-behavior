import csv

fileName = 'evaluation_log2sigmoid0to0.2.csv'
situationName = 'twoPeopleToiletSituation.xml'
newFileName = 'evaluation_log2sigmoid0to0.2-twoPeopleToiletSituation.csv'
fileNameLength = len(situationName)
print fileNameLength

f = open(fileName)
newFile = open(newFileName, 'a')
csvReader = csv.reader(f, delimiter=',', quotechar='"')

for row in csvReader:
	if row != []:
		currentSituationName = row[1][-1*(fileNameLength):]
		print currentSituationName
		if currentSituationName == situationName:
			number = row[0]
			newFile.write(number + '\n')