import csv

situationFileNames = ['pillarSituation.xml', 'standStillSituation.xml', 'twoPeopleToiletSituation.xml']

fileName = 'evaluation_log2_gaussian0.5.csv'
newFileNameBase = 'automaticresults2/evaluation_log2_gaussian0.5'

for situationName in situationFileNames:

	#situationName = 'pillarSituation.xml'
	newFileName = newFileNameBase + situationName + '.csv'
	#newFileName = 'automaticresults2/evaluation_log2_linear0.2automated-pillarSituation.csv'
	situationFileNameLength = len(situationName)
	print situationFileNameLength

	f = open(fileName)
	newFile = open(newFileName, 'a')
	csvReader = csv.reader(f, delimiter=',', quotechar='"')

	for row in csvReader:
		if row != []:
			currentSituationName = row[1][-1*(situationFileNameLength):]
			print currentSituationName
			if currentSituationName == situationName:
				number = row[0]
				newFile.write(number + '\n')
				

				
	print 'Written everything to: ' + newFileName