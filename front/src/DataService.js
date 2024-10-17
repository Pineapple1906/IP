import axios from 'axios';

function getFullUrl(url, data) {
    let currentUrl = new URL(url)
    //извлекаем поля
    const fields = Object.getOwnPropertyNames(data);
    //проходимся по каждому полю
    for (const field of fields) {

        if (field === undefined) continue
        if (field === 'id') continue
        if (field === 'date') continue
        if (field === 'countProducts') continue
        if (field === 'products') continue
        if (field === 'supplierName') continue
        currentUrl.searchParams.append(field, data[field])
    }

    return currentUrl;
}

export default class DataService {
    static mainUrl = 'http://localhost:8080/';

    static async readAll(url, transformer) {
        const response = await axios.get(this.mainUrl + url);
        return response.data.map(item => transformer(item));
    }

    static async getOrders(url){
        const response = await fetch(this.mainUrl + url)  
        const res = response.json()
        return res
    }

    static async read(url, transformer) {
        const response = await axios.get(this.mainUrl + url);
        return transformer(response.data);
    }

    static async create(url, data) {
        const response = await axios.post(getFullUrl(this.mainUrl + url, data))
        const res = response.data
        return res
    }

    static async addProduct(url) {
        await fetch(url, {
            method: 'PATCH',
        }).catch(e => console.log(e))  
        return true;
    }

    static async getSomeSuppliers(){
        const arr =[
            {
                "id":104,
                "name":"prod3",
                "cost":3333
            },
            {
                "id":153,
                "name":"prod3",
                "cost":5555
            }
        ]

        //const resArr = JSON.stringify(arr)
        //console.log(resArr)
        const response = await axios.post(this.mainUrl + `order/someSuppliers/`, {
            body: {arr}
        });
        console.log(response)
    }

    static async update(url, data) {
        await fetch(getFullUrl(this.mainUrl + url, data), {
            method: 'PATCH',
        }).catch(e => console.log(e))  
        return true;
    }

    static async delete(url) {
        const response = await axios.delete(this.mainUrl + url);
        return response.data.id;
    }
}