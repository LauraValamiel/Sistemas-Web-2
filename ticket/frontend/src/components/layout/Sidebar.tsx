import React from 'react';
import { NavLink, useNavigate } from 'react-router-dom';


const HomeIcon = () => <span>📊</span>;
const EventsIcon = () => <span>📅</span>;
const SalesIcon = () => <span>🛒</span>;
const NewSaleIcon = () => <span>➕</span>;


export const Sidebar = () => {
    const navigate = useNavigate();

    return (
        <aside className="sidebar">
            <div className="sidebar-header">
                <h2>Venda de Ingressos</h2>
            </div>
            <nav className="sidebar-nav">
                <div className="nav-link" onClick={() => navigate('/')}>
                    <HomeIcon/> Home
                </div>
                <div className="nav-link" onClick={() => navigate('/sales/events/events-list')}>
                    <EventsIcon/> Eventos
                </div>
                <div className="nav-link" onClick={() => navigate('/sales/sales-list')}>
                    <SalesIcon/> Vendas
                </div>
                <div className="nav-link" onClick={() => navigate('/sales/create')}>
                    <NewSaleIcon/> Nova Venda
                </div>
            </nav>
        </aside>
    );
};