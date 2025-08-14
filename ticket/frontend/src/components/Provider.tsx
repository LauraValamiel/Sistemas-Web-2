import React, {createContext } from 'react';

export const StoreContext = createContext({});

const StoreProvider = ({ children }: { children: React.ReactNode}) => {
    return (
        <StoreContext.Provider value={{}}>
            {children}
        </StoreContext.Provider>
    );
}
export default StoreProvider;