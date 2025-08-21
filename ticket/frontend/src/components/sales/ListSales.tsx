import React, { useState, useEffect, use } from 'react';
import api from '../../services/api';
import { useNavigate } from 'react-router-dom';

interface EventInterface {
    id: string,
    description: string
}

interface SaleInterface {
    id: string,
    saleStatus: string,
    event: EventInterface,
    userId: string
}


const ListSales = () => {
    const [sales, setSales] = useState<SaleInterface[]>([]);
    const navigate = useNavigate();
    
    const handleUpdateStatus = (saleId: string) => {
        navigate('/sales/update-status', { state: {selected: saleId } });
    }


    useEffect(() => {
        api('/sales/sales-list')
            .then(response => {
                setSales(response || []);
            })
            .catch(error => console.error('Erro ao carregar vendas:', error));
    }, []);

    const saleStatusMap: { [key: string]: string } = {
    "1": "Em aberto",
    "2": "Pago",
    "3": "Cancelado",
    "4": "Estornado"
};


    return (
    <div className="page-container">
        <div className="page-header">
            <div>
                <h2>Lista de Vendas</h2>
            </div>
        </div>

        <div className="card">
            <table className="custom-table">
                <thead>
                    <tr>
                        <th>ID da Venda</th>
                        <th>ID do Usuário</th>
                        <th>Evento</th>
                        <th>Status</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    {sales.map((sale) => (
                        <tr key={sale.id}>
                            <td>{sale.id}</td>
                            <td>{sale.userId || 'Usuário não encontrado'}</td>
                            <td>{sale.event?.description || 'Evento não encontrado'}</td>
                            <td>{saleStatusMap[sale.saleStatus] || 'Não definido'}</td>
                            <td>
                                <button className="btn btn-primary" onClick={() => handleUpdateStatus(sale.id)}>Atualizar Status</button>
                            </td>
                        </tr>
                    ))}
                </tbody>

            </table>

        </div>

    </div>
       
    )

} 

export default ListSales;
