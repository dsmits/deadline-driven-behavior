

import csv
import sets

csvFile = open('/home/djura/vakken/svn_afstudeerstage/deadline-driven-behavior/workspace/SIMOBSTest/actionlog.csv', 'rb')
actionReader = csv.reader(csvFile, delimiter=',')

# pedestrianActions will be a dictionary with as key the name of the pedestrian and as value the list of actions taken. This list will be chronological.
pedestrianActions = {}
keydict = {}

for row in actionReader:
    pedestrian = row[0]
    action = row[1]
    keydict[action] = action
    if not pedestrianActions.has_key(pedestrian):
        pedestrianActions[pedestrian] = []
    pedestrianActions[pedestrian].append(action)
    
print len(pedestrianActions.keys())

    
# Now the actions are sorted chronologically for every pedestrian, I can count for every timestep how much every action is executed.
# I need a list of dicts, in which the key is the name of the move, and the value how many pedestrians are executing it. Every element in the list represents a timestep. Probably this could have been done simultaneously in the previous for loop but this is less confusing.


actionsPerTimestep = []

for pedestrian in pedestrianActions:
    timeCounter = 0
    actions = pedestrianActions[pedestrian]
    
    for action in actions:
        if len(actionsPerTimestep) <= timeCounter:
            actionsPerTimestep.append({})
        
        if not actionsPerTimestep[timeCounter].has_key(action):
            actionsPerTimestep[timeCounter][action]=0
            
        actionsPerTimestep[timeCounter][action] += 1
        timeCounter += 1

print actionsPerTimestep[2]

csvResult = open('results.csv','wb')
writer = csv.DictWriter(csvResult, fieldnames=list(keydict), extrasaction='ignore')

writer.writerow(keydict)

for time in actionsPerTimestep:
    writer.writerow(time)
    
print keydict
