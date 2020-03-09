def drawBlock(rx,ry):
  for x in range(0,blockX):    #x 0-->33
    for y in range(0,blockY):  #y 0-->24
      px=getPixel(pict,(rx * blockX)+x,(ry * blockY)+y)
      destPx=getPixel(blankpic,(rx * blockX)+x,(ry * blockY)+y)
      color=getColor(px)
      setColor(destPx,color)

import random
import time

file=pickAFile()              #select the file to display
blank="e:/jesfiles/blankImg.png" #blank placeholder file
pict=makePicture(file)        
blankpic=makePicture(blank)
blockX=33
blockY=24
x_range=31
y_range=32

masterlist=[]

show(pict)
show(blankpic)

#fill master grid
num=0
for x in range(x_range):
  for y in range(y_range):
    listxy=[x,y]
    masterlist.append(listxy)
  num+=1

#draw 100 blocks
for i in range(100):
  mllen=len(masterlist)
  x=random.randint(0,mllen-1)      
  templist=masterlist[x]
  drawBlock(templist[0],templist[1])
  masterlist.remove(templist)
  
repaint(blankpic)
time.sleep(5)

#draw the rest of the blocks
for i in range(892):
  mllen=len(masterlist)
  x=random.randint(0,mllen-1)      
  templist=masterlist[x]
  drawBlock(templist[0],templist[1])
  masterlist.remove(templist)
  repaint(blankpic)

