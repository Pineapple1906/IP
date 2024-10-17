import { Link } from 'react-router-dom';
import React from "react";
import styles from './Toolbar.module.css';

//кнопочки
function ToolBar(props) {

    const add = () => props.add()
    const edit = () => props.edit()
    const remove = () => props.remove()

  return (
    <div className="btn-group mt-2" role="group">
        <button type="button" className={`btn btn-success ${styles.btn}`} onClick={add}
        style={{ display: props.addsVisible ? 'none' : 'block' }}>
            Добавить
        </button>
        <Link to="/createOrder">
            <button type="button" className={`btn btn-success ${styles.btn}`} onClick={add}
            style={{ display: props.addsVisible ? 'block' : 'none' }}>
                Добавить
            </button>
        </Link>
        <button type="button" className={`btn btn-warning ${styles.btn}`} onClick={edit} 
        style={{ display: props.addsVisible ? 'none' : 'block' }}>
            Изменить
        </button >
        <button type="button" className={`btn btn-danger ${styles.btn}`} onClick={remove}>
            Удалить
        </button >
    </div >

  );
}

export default ToolBar;