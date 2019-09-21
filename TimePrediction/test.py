from prediction import Prediction
import matplotlib.pyplot as plt
import numpy as np
from pylab import mpl
from excel_write import ExcelWrite
rdm=np.random

a=0.9
p=Prediction(a)
def generate_data(size=20,base=8.0):
    res=rdm.rand(size)*0.6+base
    return res
#随机生成测试时间序列
size=8
record_8=generate_data(size,8)#测试值定在8点左右 譬如8.42为八点四十二
record_9=generate_data(size,9)
record_7=generate_data(size,7)
data=np.c_[record_8,record_9,record_7].flatten()#合并在一个列表里
rdm.shuffle(data)#打乱
print(data.shape)
print(data)
res=[]#记录预测值列表
a=[]#记录学习率
for i in data:
    p.input_record_by_par(i)
    res.append(p.get_new_pre())
    a.append(p.get_a())
print(res)
cur_x=[i for i in range(24)]
next_x=[i + 1 for i in range(24)]#因为预测是第二天的 向后加1

mpl.rcParams['font.sans-serif'] = ['FangSong']
mpl.rcParams['axes.unicode_minus'] = False
plt.figure("时间预测轨迹")
#划线
plt.plot(cur_x, data, color="red",linestyle="--")#测试之真实值
plt.plot(next_x, res, color="green",linestyle="--")#测试之预测值
plt.plot(cur_x,a,color="black")
#描点
plt.scatter(cur_x, data, marker='o')
plt.scatter(next_x, res, marker='x')
plt.scatter(cur_x,a,c='black',marker='*')
plt.xlabel("次数")
plt.ylabel("时间")
plt.title("'o'--实际值\n'x'--预测值\n'*'--学习率")
plt.show()


'''
进行excel保存 这里先注释掉 
调用方法 ExcelWrite中的add(index,col_name,list):index:列数 col_name:列名 list:列数据
最后执行save()保存
excel_write=ExcelWrite("time_record")
excel_write.add(1,"开启(当前,真实)",data)
excel_write.add(2,"开启(次日,预测)",res)
excel_write.add(3,"学习率",a)
excel_write.save()


注：本代码是执行显示图片后 也就是看完图片(点右上x之后执行) 然后在本工程文件夹里面创建 time_record.xsl文件，如果还想创建，必须传入不同的名字
初始化的时候ExcelWrite(name) 传入名字
'''





