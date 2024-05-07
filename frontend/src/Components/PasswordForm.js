import {Button, Form} from 'react-bootstrap';
import {useForm} from "react-hook-form";

function PasswordForm ({onSendPassword}){
    const {
        register,
        handleSubmit,
        reset,
        formState: { errors },
    } = useForm({mode: "onChange"})

    const onSubmit = data => {
        onSendPassword(data.password);
        reset();
    }

    return (
         <Form onSubmit={handleSubmit(onSubmit)}>
            <Form.Group className="mb-3" controlId="formBasicEmail">
                <input className= "form-control"
                       type="text"
                       {...register("password", {
                           minLength: {value: 8, message: "Minimum password length is 8"},
                           maxLength: {value:35, message: "Maximum password length is 35"},
                           required: {value: true, message: "Required fields are required"},
                       })}
                       placeholder="Enter your password here" />
                <Form.Text className="text-muted">
                    {errors.comment?.message}
                </Form.Text>
            </Form.Group>
            <Button type = "submit">
                Login
            </Button>
         </Form>
     );
};

export default PasswordForm;