import React from "react";
import Tile from "./Tile";

function GameBoard({ gameBoard }) {
    return (
        <table>
            <tbody>
            {gameBoard.map((row, rowIndex) => (
                <tr key={rowIndex}>
                    {row.map((cell, cellIndex) => (
                        cell.currentState === 'GENERATED' && (
                            <td key={cellIndex} style={{ backgroundColor: cell.currentColor }}>
                                {/* You can add content here if needed */}
                            </td>
                        )
                    ))}
                </tr>
            ))}
            </tbody>
        </table>
    );
}

export default GameBoard;