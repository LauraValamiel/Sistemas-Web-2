import React from 'react';
import api from '../../services/api';

interface SaleInterface {
    id: string,
    eventId: string,
    userId: string,
    status: 'em aberto' | 'pago' | 'cancelado' | 'estornado'
}

const UpdateStatus = () => {
    const [sales, setSales] = React.useState<SaleInterface[]>([]);
    const [selectedSaleId, setSelectedSaleId] = React.useState('');
    const [newStatus, setNewStatus] = React.useState<'em aberto' | 'pago' | 'cancelado' | 'estornado'>('em aberto');

    React.useEffect(() => {
        loadSales();
    }, []);

    const loadSales = async () => {
        try {
            const response = await api('/api/sales');
            setSales(response);
        } catch (error) {
            console.error('Erro ao carregar vendas:', error);
        }
    };

    const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();
        try {
            await api(`/api/sales/${selectedSaleId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ status: newStatus })
            });
            console.log('Status atualizado com sucesso!');
            loadSales();
        } catch (error) {
            console.error('Erro ao atualizar status:', error);
        }
    };

    return (
        <div>
            <h2>Atualizar Status da Venda</h2>
            <form onSubmit={handleSubmit}>
                <select onChange={(event) => setSelectedSaleId(event.target.value)} value={selectedSaleId}>
                    <option value="">Selecione uma venda</option>
                    {sales.map((sale: any) => (
                        <option key={sale.id} value={sale.id}>{sale.user?.name || sale.userId} - {sale.eventId?.description || sale.eventId} ({sale.saleStatus})</option>
                    ))}
                </select>
                <select onChange={(event) => setNewStatus(event.target.value as any)} value={newStatus}>
                    <option value="EM_ABERTO">Em Aberto</option>
                    <option value="PAGO">Pago</option>
                    <option value="CANCELADO">Cancelado</option>
                    <option value="ESTORNADO">Estornado</option>
                </select>
                <button type="submit">Atualizar Status</button>
            </form>
        </div>
    );
};