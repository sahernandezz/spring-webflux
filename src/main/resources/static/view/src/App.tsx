import {Fragment, useEffect, useState} from 'react'
import './App.css'
import Item from './components/item/Item'
import ItemPreload from "./components/item/ItemPreload";
import Particle from "./components/particle/Particle";

function App() {

    const [items, setItems] = useState<any[]>([]);
    const [time, setTime] = useState<string>('');

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
        }, 5000);

    }, []);

    return (
        <Fragment>
            <div className="grid place-items-center bg-gray-800">
                <Particle
                    url={"google.png"}
                    height={500}
                    scale={0.55}
                    entropy={15}
                    maxParticles={10000}
                />
                <h1>{time}</h1>
            </div>
            <div className="grid grid-cols-5 gap-5 m-7">
                {items}
            </div>
        </Fragment>
    )
}

export default App
