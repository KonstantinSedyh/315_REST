let roleList = [
    {id: 1, role: "ROLE_USER"},
    {id: 2, role: "ROLE_ADMIN"}

]
let isUser = true;

let fetchUrlUsers = "http://localhost:8080/api/users";
let fetchUrlUser = "http://localhost:8080/api/user";

$(async function () {
    await getUser();
    await infoUser();
    await tittle();
    await getUsers();
    await getNewUserForm();
    await getDefaultModal();
    await createUser();

})

const userFetch = {
    head: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Referer': null
    },
    findAllUsers: async () => await fetch(fetchUrlUsers),
    findUserByUsername: async () => await fetch(fetchUrlUser),
    findOneUser: async (id) => await fetch(fetchUrlUsers + '/' + id),
    addNewUser: async (user) => await fetch(fetchUrlUsers, {method: 'POST', headers: userFetch.head, body: JSON.stringify(user)}),
    updateUser: async (user, id) => await fetch(fetchUrlUsers + '/' + id, {method: 'PUT', headers: userFetch.head, body: JSON.stringify(user)}),
    deleteUser: async (id) => await fetch(fetchUrlUsers + '/' + id, {method: 'DELETE', headers: userFetch.head})
}

async function infoUser() {
    let temp = '';
    const info = document.querySelector('#info');
    await userFetch.findUserByUsername()
        .then(res => res.json())
        .then(user => {
            temp += `
             <span style="color: white">
               ${user.email} with roles <span>${user.roles.map(e => " " + e.name.substring(5))}</span>
                </div>
            </span>
                </tr>
            `;
        });
    info.innerHTML = temp;
}