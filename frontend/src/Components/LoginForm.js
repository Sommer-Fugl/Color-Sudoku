import {Form} from 'react-bootstrap';
import {useForm} from "react-hook-form";

function LoginForm ({onSendAccount}) {
    const {
        register,
        handleSubmit,
        formState: {errors},
    } = useForm({mode: "onChange"})

    const onSubmit = (data) => {
        onSendAccount(data.login);
    }

    return (
        <Form onChange={handleSubmit(onSubmit)}>
            <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label>Player Account</Form.Label>
                <input className="form-control"
                       type="text"
                       {...register("login", {
                           minLength: {value: 2, message: "Minimum login length  is 2"},
                           maxLength: {value: 25, message: "Maximum login length is 25"},
                           required: {value: true, message: "Required fields are required"},
                       })}
                       placeholder="Enter your login here"/>
                <Form.Text className="text-muted">
                    {errors.comment?.message}
                </Form.Text>
            </Form.Group>
        </Form>
    )
}

export default LoginForm;