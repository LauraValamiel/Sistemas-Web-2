import React, { useState, useEffect } from 'react';
import api from '../../services/api';

interface UserInterface {
    id: string,
    fullName: string,
    email: string
}

const ListUsers = () => {

    //Hook: useState
    const [users, setUsers] = useState<UserInterface[]>([])

    //Hook: useEffect
    useEffect(() => {

        api('/users/users-list').then(response => {
            setUsers(response.data)
        })
        .catch(error => console.error(error))
        

    }, [])

    return(

        <div>
            <h2>Lista de usuários</h2>
            <div>
                {
                    users && users.map( user => (
                        <div>
                            <p>{user.id}</p>
                            <p>{user.fullName}</p>
                            <p>{user.email}</p>
                        </div>
                    ))
                }
            </div>
        </div>

    )

}
export default ListUsers;