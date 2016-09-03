import numpy as np
from matplotlib import pyplot as plt

randArray = np.random.randint(0, 35, 100)
print randArray

cmap = (0, 0, 0)
plt.plot(randArray, color=cmap), plt.ylabel('temperature C'), plt.xlabel('time')
plt.savefig('image.png', bbox_inches='tight', transparent='true')
plt.show()