import { BrowserRouter, Route, Routes, Navigate, createBrowserRouter } from 'react-router-dom';
// Make sure the file exists at the specified path, or update the path if needed
import CreateEvent from './components/events/CreateEvents';
import ListSales from './components/sales/ListSales';
import CreateSales from './components/sales/CreateSales';
import { UpdateStatus } from './components/sales/UpdateStatus';
import ListUsers from './components/users/ListUsers';
import { Home } from './pages/Home';
import { ListEvents } from './components/events/ListEvents';
import App, { DefaultLayout } from './App';
import Provider from './components/Provider';

export const AppRoutes = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={< DefaultLayout />} >
                    <Route index element={<Home />} />
                    <Route path="/sales/events/create" element={<CreateEvent />} />
                    <Route path="/sales/events/events-list" element={<ListEvents />} />
                    <Route path="/sales/sales-list" element={<ListSales />} />
                    <Route path="/sales/create" element={<CreateSales />} />
                    <Route path="/sales/update-status" element={<UpdateStatus />} />
                    <Route path="/users/users-list" element={<ListUsers />} />
                </Route>
            </Routes>
        </BrowserRouter>
    );
}
