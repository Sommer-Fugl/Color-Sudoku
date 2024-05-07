// import Tile from "./Tile";
//
// const GameField = ({ tiles }) => {
//     return (
//         <table className="game-field">
//             <tbody>
//                 {tiles.map((row, rowIndex) => (
//                     <tr key={`row-${rowIndex}`}>
//                         {row.map((cell, colIndex) => (
//                             <Tile/>
//                         ))}
//                     </tr>
//                 ))}
//             </tbody>
//         </table>
//     );
// };
//

import Tile from "./Tile";
import {Table} from "react-bootstrap";

const GameField = ({ tiles }) => {
    return (
        <Table className="game-field">
            <tbody>
            {tiles.map((row, rowIndex) => (
                <tr key={`row-${rowIndex}`}>
                    {row.map((cell, colIndex) => (
                        <Tile key={`cell-${rowIndex}-${colIndex}`} tile={cell} />
                    ))}
                </tr>
            ))}
            </tbody>
        </Table>
    );
};

export default GameField;
