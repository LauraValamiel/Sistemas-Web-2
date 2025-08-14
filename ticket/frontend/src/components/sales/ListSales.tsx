import React, { useState, useEffect, use } from 'react';
import api from '../../services/api';

interface SaleInterface {
    id: string,
    eventId: string,
    userId: string,
    status: 'em aberto' | 'pago' | 'cancelado' | 'estornado'
}

const ListSales = () => {
    const [sales, setSales] = useState<SaleInterface[]>([]); 

    useEffect(() => {
        api('/api/sales')
            .then(response => {
                console.log(response.data);
                setSales(response.data);
            })
            .catch(error => console.error('Erro ao carregar vendas:', error));
    }, []);


    return (
        <div>
            <h2>Lista de Vendas</h2>
            <div>
                {
                sales.map( (sales: any)  => (
                        <div>
                            <p>Id da venda: {sales.id}</p>
                            <p>Usuário: {sales.user?.name || sales.userId}</p>
                            <p>Evento: {sales.eventId?.description || sales.eventId}</p>
                            <p>Status da venda: {sales.status}</p>
                        </div>
                    ))
                }
                </div>

        </div>
    )

}