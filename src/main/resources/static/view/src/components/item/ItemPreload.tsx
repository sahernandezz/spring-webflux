const ItemPreload = () => {

    return (
        <div className="max-w-2xl px-8 py-4 bg-white rounded-lg shadow-md dark:bg-gray-800">
            <div className="flex items-center justify-between">
                <figure className="h-3 bg-slate-700 rounded col-span-2 w-1/2 animate-pulse"/>
                <figure className="h-5 px-3 py-1 transition-colors bg-slate-700 rounded w-1/3 animate-pulse"/>
            </div>

            <div className="mt-2">
                <figure className="h-3 bg-slate-700 rounded col-span-2 w-full animate-pulse"/>
                <figure className="mt-4 h-12 bg-slate-700 rounded col-span-2 w-full animate-pulse"/>
            </div>

            <div className="flex items-center justify-between mt-4">
                <figure className="h-3 bg-slate-700 rounded col-span-2 w-1/3 animate-pulse"/>

                <div className="flex items-center w-1/2">
                    <figure className="h-3 bg-slate-700 rounded col-span-2 w-full animate-pulse"/>
                </div>
            </div>
        </div>
    )
}

export default ItemPreload;