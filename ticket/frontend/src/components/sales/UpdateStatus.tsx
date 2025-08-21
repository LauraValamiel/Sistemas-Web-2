import React from 'react';
import api from '../../services/api';
import { useLocation, useNavigate } from 'react-router-dom';

interface EventInterface {
    id: string;
    description: string;
}

interface SaleInterface {
    id: string,
    event: EventInterface,
    userId: string,
    saleStatus: 'em aberto' | 'pago' | 'cancelado' | 'estornado'
}

export const UpdateStatus = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const [sale, setSales] = React.useState<SaleInterface | null >(null);
    const [newStatus, setNewStatus] = React.useState<number>(1);

    React.useEffect(() => {

        const saleId = location.state?.selected;

        const fetchSaleDetails = async () => {
            try {
                const responseData = await api(`/sales/${saleId}`);
                setSales(responseData);
                setNewStatus(responseData.saleStatus);
            } catch (err) {
                console.error('Erro ao carregar detalhes da venda:', err);
            } 
            
        };
        if(saleId){
            fetchSaleDetails();
        }

        
    }, [location.state]);

    const saleStatusMap: { [key: number]: string } = {
        1: "Em Aberto",
        2: "Pago",
        3: "Cancelado",
        4: "Estornado"
    };


    const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();
        if (!sale) {
            alert('Dados da venda não carregados.');
            return;
        }
        try {
            await api(`/sales/${sale.id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ saleStatus: newStatus })
            });
            alert('Status atualizado com sucesso!');
            navigate('/sales/sales-list');
        } catch (error) {
            console.error('Erro ao atualizar status:', error);
        }
    };

    return (
        <div className="page-container">
            <div className="page-header">
                <div>
                    <h2>Atualizar Status da Venda</h2>
                    <p>Altere o status da venda selecionada</p>
                </div>
            </div>

            <div className="card form-container">
                <div className="sale-details">
                    <h3>Detalhes da Venda</h3>
                    <p><strong>ID da Venda:</strong> {sale?.id}</p>
                    <p><strong>Evento:</strong> {sale?.event?.description}</p>
                    <p><strong>ID do Usuário:</strong> {sale?.userId}</p>
                    <p><strong>Status Atual:</strong> {saleStatusMap[sale?.saleStatus as number]}</p>
                </div>
                <hr />
                <form onSubmit={handleSubmit} className="form-body" style={{ gridTemplateColumns: '1fr' }}>
                    <div className="form-group full-width">
                        <label htmlFor="status">Novo Status</label>
                        <select id="status" onChange={(event) => setNewStatus(event.target.value as any)} value={newStatus}>
                            <option value={1}>Em Aberto</option>
                            <option value={2}>Pago</option>
                            <option value={3}>Cancelado</option>
                            <option value={4}>Estornado</option>
                        </select>
                    </div>

                    <div className="form-actions full-width">
                        <button type="submit" className="btn btn-primary">Atualizar Status</button>
                    </div>

                </form>

            </div>

        </div>
       
    );
}; 

