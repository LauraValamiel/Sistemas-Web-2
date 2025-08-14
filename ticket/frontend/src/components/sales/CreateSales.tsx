import React, { useEffect, useState } from 'react';
import axios from 'axios';
import api from '../../services/api';

interface EventInterface {
    id: string,
    tipo: string,
    descricao: string,
    data: Date,
    data_inicio_vendas: Date,
    data_fim_vendas: Date,
    preco: number
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
    status: 'em aberto' | 'pago' | 'cancelado' | 'estornado'
}

const CreateSales = () => {
    const [events, setEvents] = useState([]);
    const [users, setUsers] = useState([]);
    const [selectedEvent, setSelectedEvent] = useState('');
    const [selectedUser, setSelectedUser] = useState('');
    const [status, setStatus] = useState('EM_ABERTO');


    useEffect(() => {
        loadUsers();
        loadEvents();
    }, []);

    const loadUsers = async () => {
        try {
            const response = await api('/api/users');
            setUsers(response);
        } catch (error) {
            console.error('Erro ao carregar usuários:', error);
        }
    }

    const loadEvents = async () => {
        try {
            const response = await api('/api/events');
            setEvents(response);
        } catch (error) {
            console.error('Erro ao carregar eventos:', error);
        }
    }

    const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();
        
        if(!selectedEvent || !selectedUser) {
            console.log('Por favor, selecione um evento e um usuário.');
            return;
        }

        const dados = {
            eventId: selectedEvent,
            userId: selectedUser,
            status
        }
        try {
            const response = await axios.post('/api/sales', dados);
            console.log('Venda criada com sucesso:', response.data);
        }catch (error) {
            console.error('Erro ao criar venda:', error);
        }
    }

    return(
        <div>
            <form onSubmit={handleSubmit}>
                <h2>Cadastro de Venda de Ingressos</h2>
                <div>
                    <label htmlFor="event">Event</label>
                    <select id="event" value={selectedEvent} onChange={event => setSelectedEvent(event.target.value)} required>
                        <option value="">Selecione um evento</option>
                        {events.map((event: any) => (
                            <option key={event.id} value={event.id}>{event.tipo} - {event.descricao} - {event.data} - {event.data_inicio_vendas} - {event.data_fim_vendas} - {event.price}</option>
                        ))}
                    </select>
                </div>
                <div>
                    <label htmlFor="user">Usuário</label>
                    <select id="user" value={selectedUser} onChange={event => setSelectedUser(event.target.value)} required>
                        <option value="">Selecione um usuário</option>
                        {users.map((user: any) => (
                            <option key={user.id} value={user.id}>{user.name} - {user.email}</option>
                        ))}
                    </select>
                </div>
                <div>
                    <label htmlFor="status">Status</label>
                    <select id="status" value={status} onChange={event => setStatus(event.target.value)} required>
                        <option value="EM_ABERTO">Em aberto</option>
                        <option value="PAGO">Pago</option>
                        <option value="CANCELADO">Cancelado</option>
                        <option value="ESTORNADO">Estornado</option>
                    </select>
                </div>
                <button type="submit">Cadastrar Venda</button>
            </form>
        </div>
    )

}

export default CreateSales;