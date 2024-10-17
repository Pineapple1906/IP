import { BrowserRouter, Route, Routes} from "react-router-dom";
import CatalogProducts from "./Pages/CatalogProducts";
import CatalogSuppliers from "./Pages/CatalogSuppliers";
import OrderPage from "./Pages/OrdersPage";
import CreateOrderPage from "./Pages/CreateOrderPage";
import Header from "./general/Header";

function App() {

  return (
    <BrowserRouter>
      <Header/>
      <Routes>
        <Route path="/" Component={CatalogProducts}/>
        <Route path="/products" Component={CatalogProducts} />
        <Route path="/suppliers" Component={CatalogSuppliers} />
        <Route path="/orders" Component={OrderPage} />
        <Route path="/createOrder" Component={CreateOrderPage} />
      </Routes>

    </BrowserRouter>
  );
}

export default App;