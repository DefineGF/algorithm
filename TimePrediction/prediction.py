import numpy as np
'''
对外接口：
input_record():直接控制台输入值
inout_record_by_par(input_value):参数为输出值
get_new_pre：获取预测值
get_new_
'''
class Prediction:
    def  __init__(self,a):
        self.record=[]
        self.prediction=[]
        self.a=a
        self.A=0.0

    def input_record_by_par(self, input):
        self.record.append(input)
        if (len(self.record) > 1):
            self.init_a()
        self.init_pre()

    def input_record(self):
        new_record=input("input the time,please:")
        record_float=float(new_record)
        self.input_record_by_par(record_float)

    def init_a(self):
        pre = self.prediction[-1]
        rec = self.record[-1]
        t = (pre - rec) / rec
        # 若出现波动较大的时间参数
        if (abs(t) < 1):
            self.a = self.a * np.exp(-1 * t)
            print("学习率更新为:", self.a)
        else:
            print("保持学习率:", self.a, "(偏差太大,注意输入值!)")

    def init_A(self):
        self.A=(1-self.a)*self.A+self.a*self.record[-1]
    def init_B(self):
        data_len=len(self.record)
        if(data_len>42):
            self.B=pow(1-self.a,data_len)*self.record[0]
        else:
            self.B=pow(1-self.a,data_len)*np.mean(self.record)

    def init_pre(self):
        self.init_A()
        self.init_B()
        pre=self.A+self.B
        pre_int=int(pre)
        pre_dec=round(pre-pre_int,1)*0.60
        pre=pre_int+pre_dec
        self.prediction.append(pre)
        print("获取预测值为:",pre)

    def get_a(self):
        return self.a
    def get_new_pre(self):
        return self.prediction[-1]

