import React, { useState, useEffect } from "react";
import ToolBar from "./ToolBar";
import Modal from "./Modal";
import Table from "./Table";
import DataService from "../DataService";

function Catalog(props) {

    const [items, setItems] = useState([]);
    const [modalHeader, setModalHeader] = useState('')
    const [modalConfirm, setModalConfirm] = useState('')
    const [modalVisible, setModalVisible] = useState(false)
    const [addProdVisible, setAddProdVisible] = useState(false)
    const [isEdit, setEdit] = useState(false)
    const [isAddProd, setIsAddProd] = useState(false)


    let selectedItems = [];

    useEffect(() => {
        loadItems()
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    const loadItems = () => {
        DataService.readAll(props.url, props.transformer)
            .then(data => setItems(data))
    }

    const saveItem = () => {
        if (!isEdit) {
            DataService.create(props.url, props.data).then(() => loadItems())
        } else
            DataService.update(props.url + props.data.id, props.data).then(() => loadItems())
    }

    const add = () => {
        setEdit(false);
        setModalHeader('Добавление');
        setModalConfirm('Добавить');
        setModalVisible(true);
        props.add();
    }

    const edit = () => {
        if (selectedItems.length === 0) {
            return;
        }
        //editItem(selectedItems[0])

        DataService.read(props.url + selectedItems[0], props.transformer)
        .then(data => {
            setEdit(true);
            setModalHeader('Редактирование элемента');
            setModalConfirm('Сохранить');
            setModalVisible(true);
            props.edit(data);
        });
    }

    const editItem = (editedId) => {
        DataService.read(props.url + editedId, props.transformer)
        .then(data => {
            setEdit(true);
            setModalHeader('Редактирование элемента');
            setModalConfirm('Сохранить');
            setModalVisible(true);
            props.edit(data);
        });
    }

    const remove = () => {
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

    const addProduct = () => {
        if (selectedItems.length === 0) 
            return
        
        DataService.read(props.url + selectedItems[0], props.transformer)
        .then(data => {
            setEdit(false)
            setAddProdVisible(true)
            setModalHeader('Добавление товара в корзину')
            setModalConfirm('Сохранить')
            setAddProdVisible(true)
            props.edit(data)
        });    
    }

    const handleTableClick = (tableSelectedItems) => selectedItems = tableSelectedItems;
    const handleTableDblClick = (tableSelectedItem) => editItem(tableSelectedItem);
    const hideModal = () => {
        setModalVisible(false)
        setAddProdVisible(false)
    }
    const modalDone = () => saveItem()

  return (
    <>
        <ToolBar 
            add={add}
            edit={edit}   
            remove={remove}
            addProduct={addProduct}
            // removeProduct
            addsVisible={props.isOrder}/>
        <Table 
            headers={props.headers} 
            items={items}
            selectable={true}
            onClick={handleTableClick}
            onDblClick={handleTableDblClick}/>
        <Modal
            header={modalHeader}
            confirm={modalConfirm}
            visible={modalVisible} 
            onHide={hideModal}
            onDone={modalDone}> 
                {props.children}
        </Modal>
        <Modal            
            header={modalHeader}
            confirm={modalConfirm}
            visible={addProdVisible} 
            onHide={hideModal}
            onDone={modalDone}>
                {props.modalAddProduct}
        </Modal>
    </>
  );
}

export default Catalog;