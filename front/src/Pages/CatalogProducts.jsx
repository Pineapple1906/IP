import { useState } from 'react';
import Catalog from '../general/Catalog'
import Product from '../models/Product';

export default function CatalogProducts(props) {
    const url = 'product/'

    const transformer = (data) => new Product(data)

    const catalogProductHeaders = [
        { name: 'name', label: 'Продукт' },
        { name: 'cost', label: 'Цена' }
    ];

    const [data, setData] = useState(new Product());

    const add = () => setData(new Product());
    const edit = (data) =>  setData(new Product(data))
    

    function handleFormChange(event) {
        setData({ ...data, [event.target.id]: event.target.value })
    }

    return (
        <Catalog headers={catalogProductHeaders} 
            getAllUrl={url} 
            url={url}
            transformer={transformer}
            data={data}
            add={add}
            edit={edit}
            isOrder={false}>
            <div className="mb-3">
                <label htmlFor="name" className="form-label">Наименование</label>
                <input type="text" id="name" className="form-control" required 
                    value={data.name} onChange={handleFormChange}/>
            </div>

            <div className="mb-3">
                <label htmlFor="cost" className="form-label">Цена</label>
                <input type="number" id="cost" className="form-control" required 
                    value={data.cost} onChange={handleFormChange}/>
            </div>
        </Catalog>
    );
}