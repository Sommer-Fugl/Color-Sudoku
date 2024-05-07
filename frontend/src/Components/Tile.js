function Tile({ tile, onColorTile }) {
    let tileClass;
    if ('GENERATED'.localeCompare(tile.currentState) === 0) {
        tileClass = tile.value === undefined ? `cell` : `open${tile.value}`;
    } else {
        tileClass = tile.currentState.toLowerCase();
    }

    const handleOnClick = () => {
        onColorTile();
    };

    return (
        <td className={tileClass} onClick={handleOnClick}>
            <span>{tile.value}</span>
        </td>
    );
}
export default Tile;