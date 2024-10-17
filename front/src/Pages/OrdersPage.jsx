import { React, useState, useEffect } from "react";
import Table from "../general/Table";
import ToolBar from "../general/ToolBar";
import DataService from "../DataService";
import Order from "../models/Order";

export default function OrderPage(){
    const url = 'order/'

    const [orders, setOrders] = useState([])

    const headers = [
        { name: 'date', label: 'Дата заказа' },
        { name: 'supplierName', label: 'Поставщик' },
        { name: 'products', label: 'Продукт(ы)' }
    ];

    let selectedItems = [];

    useEffect(() => {
        loadItems()
        //eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    const loadItems = () => {
        DataService.getOrders(url).then(data => {
            setOrders([])
            data.map(order => {
                const date = new Date(order.dateOfOrder)
                order.dateOfOrder = `${date.getDate()}-${date.getMonth()}-${date.getFullYear()}`
                setOrders(prevState => [...prevState, new Order(order)])
            })
        })
    }

    const add = () => {
        console.log("add")
        loadItems()
    }
    const edit = () =>{}

    const remove = () =>{
        if (selectedItems.length === 0) 
            return

        if (window.confirm('Удалить выбранные элементы?')) {
            const promises = [];
            selectedItems.forEach(item => {
                promises.push(DataService.delete(url + item));
            });
            Promise.all(promises).then(results => {
                selectedItems.length = 0;
                loadItems();
            });
        }
    }

    const handleTableClick = (tableSelectedItems) => {selectedItems = tableSelectedItems;}
    const handleTableDblClick = (tableSelectedItem) =>{
        DataService.getSomeSuppliers()
    }

    return(
        <>
            <ToolBar 
                add={add}
                edit={edit}   
                remove={remove}
                addsVisible={true}/>

            <Table 
                headers={headers} 
                items={orders}
                selectable={true}
                onClick={handleTableClick}
                onDblClick={handleTableDblClick}/>
        </>
    )
}