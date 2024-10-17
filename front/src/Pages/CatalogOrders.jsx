import { React, useState, useEffect } from 'react';
import Catalog from '../general/Catalog'
import Order from '../models/Order';
import Supplier from '../models/Supplier';
import Product from '../models/Product';
import DataService from '../DataService';
import Modal from '../general/Modal';

export default function CatalogOrders(props) {
    const url = 'order/'
    const supplierUrl = 'supplier/'
    const productUrl = 'product/'

    const transformer = (data) => {
        new Order(data)
    }

    const catalogOrderHeaders = [
        { name: 'date', label: 'Дата заказа' },
        { name: 'supplier', label: 'Поставщик' },
        { name: 'products', label: 'Продукт(ы)' }
    ];

    const [data, setData] = useState(new Order());
    const [suppliers, setSuppliers] = useState([]);
    const [products, setProducts] = useState([]);

    useEffect(() => {
        DataService.readAll(supplierUrl, (data) => new Supplier(data)).then(data => setSuppliers(data));
        DataService.readAll(productUrl, (data) => new Product(data)).then(data => setProducts(data));
    }, [])

    const add = () =>{
        setData(new Order())
        console.log(data)
    } 
    const edit = (data) => {
        console.log(data)
        setData(new Order(data))
    }

    const addProduct = (data) => {
        setData(new Order(data))
    }

    function handleFormChange(event) {
        console.log(data)
        setData({ ...data, [event.target.id]: event.target.value })
        console.log(data)
    }

    const saveProducts = (event) => {
        console.log(products)
        setData({...products, [event.target.id]: event.target.value})
        console.log(products)
    }

    return (
        <>  
            <Catalog headers={catalogOrderHeaders} 
            url={url}
            transformer={transformer}
            data={data}
            add={add}
            edit={edit}
            isOrder={true}
            addProduct={edit}
            modalAddProduct={
                <div className="mb-3">
                    <label htmlFor="product" className="form-label">Поставщик</label>
                    <select id="product" className="form-select" required
                        value={data.product} onChange={saveProducts}>
                        <option disabled value="">Укажите продукт</option>
                        {
                            products.map(product => 
                                <option key={product.id} value={product.id}>{product.name}</option>
                            )
                        }
                    </select>
                </div>
            }>
            
            
            <div className="mb-3">
                <label htmlFor="supplierId" className="form-label">Поставщик</label>
                <select id="supplierId" className="form-select" required
                    value={data.supplierId} onChange={handleFormChange}>
                    <option disabled value="">Укажите поставщика</option>
                    {
                        suppliers.map(supplier => 
                            <option key={supplier.id} value={supplier.id}>{supplier.name}</option>
                        )
                    }
                </select>
            </div>

            <div className="mb-3">
                <label htmlFor="product" className="form-label">Поставщик</label>
                <select id="product" className="form-select" required
                    value={data.product} onChange={handleFormChange}>
                    <option disabled value="">Укажите продукт</option>
                    {
                        products.map(product => 
                            <option key={product.id} value={product.id}>{product.name}</option>
                        )
                    }
                </select>
            </div>
        </Catalog>
        </>
    );
}