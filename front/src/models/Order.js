import Product from "./Product";

export default class Order {
    constructor(data) {
        this.id = data?.id;
        this.date = data?.dateOfOrder || '';
        this.supplierId = data?.supplier.id || '';
        this.supplierName = data?.supplier.name || '';
        this.products = data?.products || [];
    }
}