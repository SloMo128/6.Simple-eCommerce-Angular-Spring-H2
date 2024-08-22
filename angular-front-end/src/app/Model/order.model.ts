export class Order {
    constructor(
        public id: number,
        public dataCreated: Date,
        public status: number,
    ){}
}