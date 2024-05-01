import {Students} from "./Students";


export interface Payment{

  id: number,
  date: Date,
  amount : number,
  type :  TypePayment,
  status : StatusPayments,
  file : string,
  student : Students,


}

export enum TypePayment {
  CASH,
  CHECK,
  TRANSFER
}
export enum StatusPayments {
  CREATED,
  VALIDATED,
  REJECTED
}
