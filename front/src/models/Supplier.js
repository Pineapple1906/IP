export default class Supplier {
    constructor(data) {
        this.id = data?.id;
        this.name = data?.name || '';
        this.license = data?.license || '';
    }
}