class TipCalc():
    def __init__(self, tip=0.18):
        self.tip = tip
        self.tip_array = []

    def add_to_total(self, bill_amount):
        self.tip_array.append(self.tip * bill_amount)

    def get_total_tips(self):
        return reduce(lambda x, y : x + y, self.tip_array)


if __name__ == '__main__':

test_tc = TipOutCalc()
test_tc.add_to_total(60.24)
test_tc.add_to_total(51.28)
