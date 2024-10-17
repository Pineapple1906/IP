export default class Product {
    constructor(data) {
        this.id = data?.id;
        this.name = data?.name || '';
        this.cost = data?.cost || '';
    }
}