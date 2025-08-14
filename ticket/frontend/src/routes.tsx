import { BrowserRouter, Route, Routes, Navigate } from 'react-router-dom';
// Make sure the file exists at the specified path, or update the path if needed
import CreateEvent from './components/events/CreateEvents';
import ListSales from './components/sales/ListSales';
import CreateSales from './components/sales/CreateSales';
import UpdateStatus from './components/sales/UpdateStatus';
import ListUsers from './components/users/ListUsers';

export const AppRoutes = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Navigate to="/events/create" />} />
                <Route path="/events/create" element={<CreateEvent />} />
                <Route path="/sales/sales-list" element={<ListSales />} />
                <Route path="/sales/create" element={<CreateSales />} />
                <Route path="/sales/update-status" element={<UpdateStatus />} />
                <Route path="/users/users-list" element={<ListUsers />} />
            </Routes>
        </BrowserRouter>
    );
}