export type NewOrder = {
  orderId: number;
  dateOfOrder: any;
  timeOfOrder: any;
  noOfItems: number;
  amount: number;
  menu: any;
  address: {
    street: string | any;
    city: string | any;
    houseNo: string | any;
    pinCode: string | any;
  };
};
