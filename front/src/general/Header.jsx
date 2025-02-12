import { Link } from 'react-router-dom';

export default function Header() {
    return (
        <nav className="navbar navbar-expand-lg bg-light">
            <div className="container-fluid">
                <h1 className="navbar-brand">Магазин</h1>
                <button className="navbar-toggler" type="button"
                    data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav"> 
                        <li className="nav-item">
                            <Link className="nav-link" to="/products">
                                Товары
                            </Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to="/suppliers">
                                Производители
                            </Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to="/orders">
                                Заказы
                            </Link>
                        </li>
                    </ul>
                </div>
            </div>
        </nav >
    );
}