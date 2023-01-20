import {Fragment, useEffect, useState} from 'react'
import './App.css'
import Item from './components/item/Item'
import ItemPreload from "./components/item/ItemPreload";

function App() {

    const [items, setItems] = useState<any[]>([]);

    useEffect(() => {
        const listPreLoad: any[] = [];
        for (let i = 0; i < 10; i++) {
            listPreLoad.push(<ItemPreload/>);
        }
        setItems(listPreLoad);

        const listI: any[] = [];
        setTimeout(() => {
            for (let i = 0; i < 10; i++) {
                listI.push(<Item/>);
            }
            setItems(listI);
        }, 3000);

    }, []);


    return (
        <Fragment>
            <div className="grid grid-cols-5 gap-5 m-7">
                {items}
            </div>
        </Fragment>
    )
}

export default App
