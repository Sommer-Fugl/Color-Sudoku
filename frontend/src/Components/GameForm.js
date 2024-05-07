import {Button, Form} from 'react-bootstrap';
import {useForm} from "react-hook-form";

function GameForm ({onSendDifficulty}) {
    const {
        register,
        handleSubmit,
        formState: {errors},
    } = useForm({mode: "onChange"})

    const onSubmit = (data) => {
        onSendDifficulty(Number(data.btnradio));
    }

    return (
        <Form onChange={handleSubmit(onSubmit)}>
            <Form.Group className="mb-3" controlId="formBasicEmail">
                <div className="btn-group" role="group" aria-label="Basic radio toggle button group">
                    <input
                        type="radio"
                        className="btn-check"
                        name="btnradio"
                        id="btnradio1"
                        value="1"
                        autoComplete="off"
                        {...register("btnradio", {required: true})}
                        defaultChecked
                    />
                    <label className="btn btn-outline-primary" htmlFor="btnradio1">EASY</label>

                    <input
                        type="radio"
                        className="btn-check"
                        name="btnradio"
                        id="btnradio2"
                        value="2"
                        autoComplete="off"
                        {...register("btnradio", {required: true})}
                    />
                    <label className="btn btn-outline-primary" htmlFor="btnradio2">MEDIUM</label>

                    <input
                        type="radio"
                        className="btn-check"
                        name="btnradio"
                        id="btnradio3"
                        value="3"
                        autoComplete="off"
                        {...register("btnradio", {required: true})}
                    />
                    <label className="btn btn-outline-primary" htmlFor="btnradio3">HARD</label>
                    <input
                        type="radio"
                        className="btn-check"
                        name="btnradio"
                        id="btnradio4"
                        value="4"
                        autoComplete="off"
                        {...register("btnradio", {required: true})}
                    />
                    <label className="btn btn-outline-primary" htmlFor="btnradio4">EXPERT</label>
                </div>
                {errors.btnradio && <p>Your selection is required</p>}
            </Form.Group>
        </Form>
    )
}

export default GameForm;