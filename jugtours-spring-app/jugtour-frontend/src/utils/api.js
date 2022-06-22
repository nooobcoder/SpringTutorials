const req = Object.assign(
  {
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
    },
  },
);

const getGroups = async () => {
  const url = `${process.env.API_URL || 'http://127.0.0.1:4848'}/api/groups`;
  const response = await fetch(url, req);
  const groups = await response.json();
  return groups;
}

const getById = async (id) => {
  if (id !== `new`) {
    const url = `${process.env.API_URL || 'http://127.0.0.1:4848'}/api/group/${id}`;
    const response = await fetch(url, req);
    const group = await response.json();
    return group;
  }
}

const removeGroup = async (id) => {
  const url = `${process.env.API_URL || 'http://127.0.0.1:4848'}/api/group/${id}`;
  // Try catch
  try {
    const response = await fetch(url, {...req, method: 'DELETE'});
    const result = await response.json();
    return result;
  } catch (e) {
    console.log(e);
  }
}

export {getGroups, removeGroup, getById}