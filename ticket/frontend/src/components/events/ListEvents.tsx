import React, { useState, useEffect, use } from 'react';
import api from '../../services/api';
import { useNavigate } from 'react-router-dom';

interface EventInterface {
    id: string,
    type: string,
    description: string,
    date: Date,
    startSales: Date,
    endSales: Date,
    price: number
}

export const ListEvents = () => {
    const [events, setEvents] = useState<EventInterface[]>([]);
    const navigate = useNavigate(); 

    useEffect(() => {
        api('/events/events-list')
            .then(response => {
                console.log("Resposta da API:", response);
                setEvents(response || []);
            })
            .catch(error => console.error('Erro ao carregar eventos:', error)); 
    }, []);

    const eventTypeMap: { [key: string]: string } = {
    "1": "Palestra",
    "2": "Show",
    "3": "Teatro",
    "4": "Cinema",
    "5": "Festa",
    "6": "Festival",
    "7": "Outros"
    };


    return (
    <div className="page-container">
        <div className="page-header">
            <div>
                <h2>Lista de Eventos</h2>
            </div>
            <button className="btn btn-primary" onClick={() => navigate('/sales/events/create')}>+ Novo Evento</button>
        </div>

            <div className="events-grid">
                {Array.isArray(events) && events.map((events) => (
                    <div key={events.id} className="card event-card">
                        <div className="event-card-header">
                            <h3>{events.description}</h3>          
                        </div>
                        <div className="event-card-details">
                            <p><strong>Tipo:</strong> {eventTypeMap[events.type]}</p>
                            <p><strong>Data:</strong> {new Date(events.date).toLocaleString()}</p>
                            <p><strong>Início das Vendas:</strong> {new Date(events.startSales).toLocaleString()}</p>
                            <p><strong>Fim das Vendas:</strong> {new Date(events.endSales).toLocaleString()}</p>
                            <p><strong>Preço:</strong> R$ {events.price}</p>
                    </div>
                    </div>
                ))}
            </div>   
    </div>
    )

}