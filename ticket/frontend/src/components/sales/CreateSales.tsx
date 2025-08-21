import React, { useEffect, useState } from 'react';
import api from '../../services/api';

interface EventInterface {
    id: string,
    type: string,
    description: string,
    date: Date,
    startSales: Date,
    endSales: Date,
    price: number
}

interface UserInterface {
    id: string,
    name: string,
    email: string
}

interface SaleInterface {
    id: string,
    eventId: EventInterface,
    userId: UserInterface,
    saleStatus: 'em aberto' | 'pago' | 'cancelado' | 'estornado'
}

const CreateSales = () => {
    const [events, setEvents] = useState([]);
    const [users, setUsers] = useState([]);
    const [selectedEvent, setSelectedEvent] = useState('');
    const [selectedUser, setSelectedUser] = useState('');
    const [status, setStatus] = useState("1");


    useEffect(() => {
        loadUsers();
        loadEvents();
    }, []);

    const loadUsers = async () => {
        try {
            const data = await api('/users/users-list', {
                method: 'GET'
            });
            setUsers(data);
        } catch (error) {
            console.error('Erro ao carregar usuários:', error);
        }
    }

    const loadEvents = async () => {
        try {
            const data = await api('/events/events-list', {
                method: 'GET'
            });
            setEvents(data);
        } catch (error) {
            console.error('Erro ao carregar eventos:', error);
        }
    }

    const eventTypeMap: { [key: string]: string } = {
    "1": "Palestra",
    "2": "Show",
    "3": "Teatro",
    "4": "Cinema",
    "5": "Festa",
    "6": "Festival",
    "7": "Outros"
    };

    const saleStatusMap: { [key: string]: number } = {
        "Em aberto": 1,
        "Pago": 2,
        "Cancelado": 3,
        "Estornado": 4
    };

    const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();
        
        if(!selectedEvent || !selectedUser) {
            console.log('Por favor, selecione um evento e um usuário.');
            return;
        }

        const dados = {
            eventId: selectedEvent,
            userId: selectedUser,
            saleStatus: Number(status)
        }

        console.log("Dados enviados:", dados);

        try {
            const response = await api('/sales/createSales', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(dados),
            });
            console.log('Venda criada com sucesso:', response);
        }catch (error) {
            console.error('Erro ao criar venda:', error);
        }
    }

    return(
    <div className="page-container">
        <div className="page-header">
            <div>
                <h2>Cadastrar Nova Venda de Ingressos</h2>
            </div>
        </div>

        <div className="card form-container">
            <div className="form-header">
                <h3>Dados da Venda</h3>
                <p>Selecione o evento e o cliente</p>
            </div>
            <form onSubmit={handleSubmit} className="form-body">
                <div className="form-group full-width">
                    <label htmlFor="event">Evento</label>
                    <select id="event" value={selectedEvent} onChange={event => setSelectedEvent(event.target.value)} required>
                        <option value="" disabled>Selecione um evento</option>
                        {events.map((event: any) => (
                            <option key={event.id} value={event.id}>
                                {eventTypeMap[event.type]} - {event.description} - {new Date(event.date).toLocaleString()} - {new Date(event.startSales).toLocaleString()} - {new Date(event.endSales).toLocaleString()} - R$ {event.price}
                            </option>
                        ))}
                    </select>
                </div>

                <div className="form-group full-width">
                    <label htmlFor="user">Usuário</label>
                    <select id="user" value={selectedUser} onChange={event => setSelectedUser(event.target.value)} required>
                        <option value="" disabled>Selecione um usuário</option>
                        {users.map((user: any) => (
                            <option key={user.id} value={user.id}>{user.name} - {user.email}</option>
                        ))}
                    </select>
                </div>

                <div className="form-group">
                    <label htmlFor="Status">Status Inicial</label>
                    <select id="status" value={status} onChange={event => setStatus(event.target.value)} required>
                        <option value={1}>Em aberto</option>
                        <option value={2}>Pago</option>
                        <option value={3}>Cancelado</option>
                        <option value={4}>Estornado</option>
                    </select>
                </div>

                <div className="form-actions full-width">
                    <button type="button" className="btn bt-secondary">Cancelar</button>
                    <button type="submit" className="btn btn-primary">Cadastrar Venda</button>
                </div>
            </form>
        </div>
    </div>
        
    )

}

export default CreateSales;
