import { React, useState, useEffect } from "react";
import { Link } from 'react-router-dom';
import Supplier from "../models/Supplier";
import DataService from "../DataService";
import Order from "../models/Order";
import Product from "../models/Product";
import Table from "../general/Table";
import Modal from "../general/Modal";

export default function CreateOrderPage(props){
    const url = 'order/'
    const supplierUrl = 'supplier/'
    const productUrl = 'product/'

    let selectedItems = [];

    const headers = [
        { name: 'name', label: 'Продукт' },
        { name: 'cost', label: 'Цена' }
    ];

    const transformer = (data) => new Order(data)
    const transformerSupplier = (data) => new Supplier(data)
    const transformerProduct = (data) => new Product(data)

    const [suppliers, setSuppliers] = useState([])
    const [products, setProducts] = useState([])

    const [order, setOrder] = useState(new Order())
    const [addsProduct, setAddsProduct] = useState(new Product())

    const [modalHeader, setModalHeader] = useState('')
    const [modalConfirm, setModalConfirm] = useState('')
    const [modalVisible, setModalVisible] = useState(false)


    useEffect(() => {
        loadItems()
        //eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    const loadItems = () => {
        DataService.readAll(supplierUrl, transformerSupplier).then(data => setSuppliers(data))
        DataService.readAll(productUrl, transformerProduct).then(data => setProducts(data))
    }

    const createOrder = () => {
        if(order.supplierId === ''){
            window.alert ('Заказ был заполнен неверно, попробуйте еще раз')
            return 
        }
        DataService.create(url, order)
        .then(data => {
            order.products.map(product =>{
                DataService.addProduct(`http://localhost:8080/${url}addProduct/${data}?productId=${product.id}`)
            })
        })
    }

    const addProductInOrder = () => {
        DataService.read(`${productUrl}${addsProduct.id}`, transformerProduct)
        .then(data => {
            let contains = false
            order.products.map(product => {
                if(product.id === data.id) contains = true
            })
            if(!contains){
                order.products.push(data)
                setOrder({ ...order, products: order.products })
            }
            else 
                window.alert ('Такой продукт уже был добавлен')
        })
    }

    const removeProduct = () => {
        if (selectedItems.length === 0) {
            return;
        }

        if (window.confirm('Удалить выбранные элементы?')) {
            const promises = [];
            selectedItems.forEach(item => {
                promises.push(DataService.delete(props.url + item));
            });
            Promise.all(promises).then((results) => {
                selectedItems.length = 0;
                loadItems();
            });
        }
    }

    const handleFormChange = (event) => {
        setOrder({ ...order, [event.target.id]: event.target.value })
    }

    const handleAddProduct = (event) => {
        setAddsProduct({ ...addsProduct, [event.target.id]: event.target.value })
    }

    const addProduct = () => {
        setAddsProduct(new Product())
        setModalHeader('Добавление продукта');
        setModalConfirm('Добавить');
        setModalVisible(true);
    }

    const hideModal = () => setModalVisible(false)
    const ds = () => console.log("")

    const handleTableClick = (tableSelectedItems) => selectedItems = tableSelectedItems;

    return(
        <div className="container">
            <div className="row">
                <h1 className="display-6">Создание заказа</h1>
            </div>

            <div className="row gx-5">
                <div className="btn-group" role="group" aria-label="Basic mixed styles example">
                    <Link to="/orders">
                        <button type="button" className="btn btn-success" onClick={createOrder}>Создать</button>
                        <button type="button" className="btn btn-danger">Отмена</button>
                    </Link>

                </div>
            </div>
            <br></br>
            <div className="mb-3">
                <p className="h4" htmlFor="supplierId">Поставщик</p>
                <select id="supplierId" className="form-select " required
                    value={order.supplierId} onChange={handleFormChange}>
                    <option disabled value="">Укажите поставщика</option>
                    {
                        suppliers.map(supplier => 
                            <option key={supplier.id} value={supplier.id}>{supplier.name}</option>
                        )
                    }
                </select>
            </div>
            <p className="h4">Продукты</p>
            <div className="btn-group" role="group" aria-label="Basic mixed styles example">
                <button type="button" className="btn btn-success" onClick={addProduct}>Добавить продукт</button>
                <button type="button" className="btn btn-danger" onClick={removeProduct} >Удалить продукт</button>
            </div>
            
            <Table 
            headers={headers} 
            items={order.products}
            selectable={true}
            onClick={handleTableClick}
            onDblClick={ds}/>

            <Modal
                header={modalHeader}
                confirm={modalConfirm}
                visible={modalVisible} 
                onHide={hideModal}
                onDone={addProductInOrder}>
                    <div className="mb-3">
                        <p className="h4" htmlFor="id">Продукт</p>
                        <select id="id" className="form-select " required
                            value={addsProduct.id} onChange={handleAddProduct}>
                            <option disabled value="">Укажите продукт</option>
                            {
                                products.map(product => 
                                    <option key={product.id} value={product.id}>{product.name}</option>
                                )
                            }
                        </select>
                    </div>  
            </Modal>
        </div>
    )
}