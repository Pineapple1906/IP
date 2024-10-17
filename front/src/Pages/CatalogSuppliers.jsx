import { useState } from 'react';
import Catalog from '../general/Catalog'
import Supplier from '../models/Supplier';

export default function CatalogSuppliers(props) {
    const url = 'supplier/'

    const transformer = (data) => new Supplier(data)

    const catalogProductHeaders = [
        { name: 'name', label: 'Поставщик' },
        { name: 'license', label: 'Лицензия' }
    ];

    const [data, setData] = useState(new Supplier());

    const add = () => setData(new Supplier());
    const edit = (data) => setData(new Supplier(data))

    const handleFormChange = (event) => {
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
                <label htmlFor="name" className="form-label">Поставщик</label>
                <input type="text" id="name" className="form-control" required 
                    value={data.name} onChange={handleFormChange}/>
            </div>

            <div className="mb-3">
                <label htmlFor="license" className="form-label">Лицензия</label>
                <input type="number" id="license" className="form-control" required 
                    value={data.license} onChange={handleFormChange}/>
            </div>
        </Catalog>
    );
}