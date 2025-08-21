import React, { use, useContext, useEffect, useState } from 'react';


import CreateEvents from '../components/events/CreateEvents';
import CreateSales from '../components/sales/CreateSales';
import ListSales from '../components/sales/ListSales';
import ListUsers from '../components/users/ListUsers';
import { useNavigate } from 'react-router-dom';
import { StoreContext } from '../components/Provider';
import api from '../services/api';


export const Home: React.FC = () => {

    const navigate = useNavigate();
    const context = useContext(StoreContext);
    const [totalEvents, setTotalEvents] = useState(0);
    const [totalSales, setTotalSales] = useState(0);
    const [pendingSales, setPendingSales] = useState(0);

    useEffect(() => {
        const dados = async () => {
            try{
                const [eventsResponse, salesResponse] = await Promise.all([
                    api('/events/events-list'),
                    api('/sales/sales-list')
                ]);

                setTotalEvents(eventsResponse.length);
                setTotalSales(salesResponse.length);

                const pending = salesResponse.filter(sale => sale.status === 'EM_ABERTO').length;
                setPendingSales(pending);
            }catch (error){
                console.error("Erro ao buscar os dados: ", error);
            }
        };
        dados();
    }, [])

    if (!context){
        throw new Error('StoreContext is not available');
    }

    return (
        <div className="dashboard-container">
            <div className="dashboard-header">
                <h1>Bem-vindo ao Sistema de Vendas de Ingressos!</h1>
                <h2>Visão geral do sistema</h2>
            </div>

            <div className="stats-grid">
                <div className="stat-card">
                    <div className="stat-card-header">
                        <p>Total de Eventos</p>
                        <h2 className="stat-number">{totalEvents}</h2>
                    </div>
                </div>
            </div>
             <div className="stats-grid">
                <div className="stat-card">
                    <div className="stat-card-header">
                        <p>Total de Vendas</p>
                        <h2 className="stat-number">{totalSales}</h2>
                    </div>
                </div>
            </div>
             <div className="stats-grid">
                <div className="stat-card">
                    <div className="stat-card-header">
                        <p>Vendas Pendentes</p>
                        <h2 className="stat-number">{pendingSales}</h2>
                    </div>
                </div>
            </div>
        </div>
    );

}